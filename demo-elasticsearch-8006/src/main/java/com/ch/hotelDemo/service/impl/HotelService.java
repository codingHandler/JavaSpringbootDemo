package com.ch.hotelDemo.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.hotelDemo.mapper.HotelMapper;
import com.ch.hotelDemo.pojo.Hotel;
import com.ch.hotelDemo.pojo.HotelDoc;
import com.ch.hotelDemo.pojo.PageResult;
import com.ch.hotelDemo.pojo.RequestParams;
import com.ch.hotelDemo.service.IHotelService;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.util.*;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private IHotelService hotelService;

    @Override
    public PageResult search(RequestParams params) {

        try {
            // 1,准备Request
            SearchRequest searchRequest = new SearchRequest("hotel");
            // 2,准备DSL
            buildBasicQuery(params, searchRequest);

            // 2.2,分页
            int page = params.getPage();
            int size = params.getSize();
            searchRequest.source().from((page - 1) * size).size(size);
            if (StringUtils.hasText(params.getLocation())) {
                // 2.3,位置排序
                searchRequest.source().sort(SortBuilders.geoDistanceSort("location",
                        new GeoPoint(params.getLocation()))
                        .order(SortOrder.ASC)
                        .unit(DistanceUnit.KILOMETERS));
            }
            // 3,发送请求
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            // 4,解析响应
            return handleResponse(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, List<String>> filters(RequestParams params) {
        // 准备请求Request
        SearchRequest searchRequest = new SearchRequest("hotel");
        // 准备DSL
        searchRequest.source().size(0);
        // query信息,限定聚合范围
        buildBasicQuery(params, searchRequest);
        // 聚合
        buildAggregation(searchRequest);

        HashMap<String, List<String>> map = new HashMap<>();
        try {
            // 请求
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            // 获取聚合结果
            ArrayList<String> cityLists = getAggByName(search, "cityAgg");
            map.put("city", cityLists);

            ArrayList<String> arrayList = getAggByName(search, "starAgg");
            map.put("starName", arrayList);

            ArrayList<String> brandLists = getAggByName(search, "brandAgg");
            map.put("brand", brandLists);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public int saveById(Long id) {
        // 获取数据
        Hotel hotel = hotelService.getById(id);
        HotelDoc hotelDoc = new HotelDoc(hotel);
        String json = JSON.toJSONString(hotelDoc);
        // 1,准备请求
        IndexRequest indexRequest = new IndexRequest("hotel").id(id.toString());
        // 2,准备DSL
        indexRequest.source(json, XContentType.JSON);
        try {
            // 发起请求
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int deleteById(Long id) {
        // 准备请求
        DeleteRequest deleteRequest = new DeleteRequest("hotel");
        deleteRequest.id(id.toString());
        try {
            // 发送请求
            restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    void buildAggregation(SearchRequest searchRequest) {
        searchRequest.source().aggregation(
                AggregationBuilders
                        .terms("cityAgg")
                        .field("city")
                        .size(10));
        searchRequest.source().aggregation(
                AggregationBuilders
                        .terms("starAgg")
                        .field("starName")
                        .size(10));
        searchRequest.source().aggregation(
                AggregationBuilders
                        .terms("brandAgg")
                        .field("brand")
                        .size(10));
    }

    ArrayList<String> getAggByName(SearchResponse search, String aggName) {
        Terms brandAgg = search.getAggregations().get(aggName);
        List<? extends Terms.Bucket> buckets = brandAgg.getBuckets();
        ArrayList<String> arrayList = new ArrayList<>();
        buckets.forEach(item -> {
            arrayList.add(item.getKeyAsString());
        });
        return arrayList;
    }

    void buildBasicQuery(RequestParams params, SearchRequest searchRequest) {
        // 2.1,构建BoolQuery
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        String key = params.getKey();
        if (StringUtils.isEmpty(key)) {
            boolQuery.must(QueryBuilders.matchAllQuery());
        } else {
            boolQuery.must(QueryBuilders.matchQuery("all", params.getKey()));
        }
        // 品牌判断
        if (StringUtils.hasText(params.getCity())) {
            boolQuery.must(QueryBuilders.termQuery("city", params.getCity()));
        }
        /**
         * must和filter的区别:
         * 同样是按条件匹配，filter不统计相关度，must统计相关度,简而言之,就是must会影响得分,filter不会
         * must比filter计算更复杂，更耗时
         */
        // 城市判断
        if (StringUtils.hasText(params.getBrand())) {
            boolQuery.filter(QueryBuilders.termQuery("brand", params.getBrand()));
        }
        // 星级判断
        if (StringUtils.hasText(params.getStarName())) {
            boolQuery.filter(QueryBuilders.termQuery("starName", params.getStarName()));
        }
        // 价格判断
        if (params.getMinPrice() != null && params.getMaxPrice() != null) {
            boolQuery.filter(QueryBuilders.rangeQuery("price")
                    .gte(params.getMinPrice()).lte(params.getMaxPrice()));
        }
        // 2,2算分控制
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(
                // 原始查询,相关性算分查询
                boolQuery,
                // function score 的数组
                new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                        // 其中一个function score原始
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                // 过滤条件
                                QueryBuilders.termQuery("isAD", true),
                                // 算分函数
                                ScoreFunctionBuilders.weightFactorFunction(10)
                        )
                });
        searchRequest.source().query(functionScoreQueryBuilder);


    }


    private PageResult handleResponse(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        // 4.1.总条数
        long total = searchHits.getTotalHits().value;
        // 4.2.获取文档数组
        SearchHit[] hits = searchHits.getHits();
        // 4.3.遍历
        List<HotelDoc> hotels = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            // 4.4.获取source
            String json = hit.getSourceAsString();
            // 4.5.反序列化，非高亮的
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            // 4.6.处理高亮结果
            // 1)获取高亮map
            Map<String, HighlightField> map = hit.getHighlightFields();
            if (CollectionUtils.isEmpty(map)) {
                // 2）根据字段名，获取高亮结果
                HighlightField highlightField = map.get("name");
                if (highlightField != null) {
                    // 3）获取高亮结果字符串数组中的第1个元素
                    String hName = highlightField.getFragments()[0].toString();
                    // 4）把高亮结果放到HotelDoc中
                    hotelDoc.setName(hName);
                }
            }
            // 4.8.排序信息
            Object[] sortValues = hit.getSortValues();
            if (sortValues.length > 0) {
                hotelDoc.setDistance(sortValues[0]);
            }

            // 4.9.放入集合
            hotels.add(hotelDoc);
        }
        return new PageResult(total, hotels);
    }


}
