package com.ch.hotelDemo;

import com.alibaba.fastjson.JSON;
import com.ch.hotelDemo.pojo.HotelDoc;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author: ch
 * @date: 2022/6/1 10:48
 * @description: TODO
 */
@SpringBootTest
public class HotelQueryTest {

    @Autowired
    private RestHighLevelClient client;

    @Test
    void matchAll() throws IOException {
        // 1,准备请求Request
        SearchRequest request = new SearchRequest("hotel");
        // 2,准备资源DSL
        request.source().query(QueryBuilders.matchAllQuery());
     /*   SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // 2,准备DSL
        request.source(searchSourceBuilder);*/
        // 3,发送请求
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        // 4,解析响应
        handleResponse(search);
    }

    @Test
    void match() throws IOException {
        // 1,准备请求Request
        SearchRequest request = new SearchRequest("hotel");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("all", "如家"));
        // 2,准备资源DSL
        request.source(searchSourceBuilder);
        // 3,发送请求
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        // 4,解析响应
        handleResponse(search);
    }

    // 多字段查询
    @Test
    void multiMatch() throws IOException {
        // 1,准备请求Request
        SearchRequest request = new SearchRequest("hotel");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("如家", "name","business"));
        // 2,准备资源DSL
        request.source(searchSourceBuilder);
        // 3,发送请求
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        // 4,解析响应
        handleResponse(search);
    }


    // 精确查询:
    // 一般搜的是不分词字段,因此一般只对keyword,boolean,日期,数字,等类型进行精确查询
    @Test
    void termQuery() throws IOException {
        // 1,准备请求Request
        SearchRequest request = new SearchRequest("hotel");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("price","159"));
        // 2,准备资源DSL
        request.source(searchSourceBuilder);
        // 3,发送请求
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        // 4,解析响应
        handleResponse(search);
    }
    // 范围查询
    @Test
    void rangeQuery() throws IOException {
        // 1,准备请求Request
        SearchRequest request = new SearchRequest("hotel");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.rangeQuery("price").gte(100).lte(150));
        // 2,准备资源DSL
        request.source(searchSourceBuilder);
        // 3,发送请求
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        // 4,解析响应
        handleResponse(search);
    }

    // 布尔查询
    @Test
    void boolQuery() throws IOException {
        // 1,准备请求Request
        SearchRequest request = new SearchRequest("hotel");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("city","上海"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(250));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        // 2,准备资源DSL
        request.source(searchSourceBuilder);
        // 3,发送请求
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        // 4,解析响应
        handleResponse(search);
    }
    // 分页查询
    @Test
    void testPageAndSort() throws IOException {
        // 页码，每页大小
        int page = 1, size = 5;
        // 1.准备Request
        SearchRequest request = new SearchRequest("hotel");
        // 2.准备DSL
        // 2.1.query
        request.source().query(QueryBuilders.matchAllQuery());
        // 2.2.排序 sort
        request.source().sort("price", SortOrder.ASC);
        // 2.3.分页 from、size
        request.source().from((page - 1) * size).size(5);
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析响应
        handleResponse(response);

    }
    // 高亮查询
    @Test
    void testHighlight() throws IOException {
        // 页码，每页大小
        int page = 1, size = 5;
        // 1.准备Request
        SearchRequest request = new SearchRequest("hotel");
        // 2.准备DSL
        // 2.1.query
        request.source().query(QueryBuilders.matchQuery("all","如家"));
        // 高亮
        request.source().highlighter(new HighlightBuilder().field("name").requireFieldMatch(false));
        // 2.2.排序 sort
        request.source().sort("price", SortOrder.ASC);
        // 2.3.分页 from、size
        request.source().from((page - 1) * size).size(5);

        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析响应
        handleResponse(response);

    }






    private void handleResponse(SearchResponse response) {
        // 4.解析响应
        SearchHits searchHits = response.getHits();
        // 4.1.获取总条数
        long total = searchHits.getTotalHits().value;
        System.out.println("共搜索到" + total + "条数据");
        // 4.2.文档数组
        SearchHit[] hits = searchHits.getHits();
        // 4.3.遍历
        for (SearchHit hit : hits) {
            // 获取文档source
            String json = hit.getSourceAsString();
            // 反序列化
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            // 获取高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (!CollectionUtils.isEmpty(highlightFields)) {
                // 根据字段名获取高亮结果
                HighlightField highlightField = highlightFields.get("name");
                if (highlightField != null) {
                    // 获取高亮值
                    String name = highlightField.getFragments()[0].string();
                    // 覆盖非高亮结果
                    hotelDoc.setName(name);
                }
            }
            System.out.println("hotelDoc = " + hotelDoc);
        }
    }

    @BeforeEach
    void setUp() {
        client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://159.75.251.227:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        client.close();
    }
}