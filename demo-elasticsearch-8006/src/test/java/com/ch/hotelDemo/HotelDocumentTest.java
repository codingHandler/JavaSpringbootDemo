package com.ch.hotelDemo;


import com.alibaba.fastjson.JSON;
import com.ch.hotelDemo.pojo.Hotel;
import com.ch.hotelDemo.pojo.HotelDoc;
import com.ch.hotelDemo.service.IHotelService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ch.hotelDemo.constants.HotelIndexConstants.MAPPING_TEMPLATE;

@SpringBootTest
class HotelDocumentTest {

    private RestHighLevelClient client;

    @Autowired
    private IHotelService hotelService;

    @Test
    void testAddDocument() throws IOException {
        // 1.查询数据库hotel数据
        Hotel hotel = hotelService.getById(61083L);
        // 2.转换为HotelDoc
        HotelDoc hotelDoc = new HotelDoc(hotel);
        // 3.转JSON
        String json = JSON.toJSONString(hotelDoc);

        // 1.准备Request
        IndexRequest request = new IndexRequest("hotel").id(hotelDoc.getId().toString());
        // 2.准备请求参数DSL，其实就是文档的JSON字符串
        request.source(json, XContentType.JSON);
        // 3.发送请求
        client.index(request, RequestOptions.DEFAULT);
    }

    @Test
    void testGetDocumentById() throws IOException {
        // 1.准备Request      // GET /hotel/_doc/{id}
        GetRequest request = new GetRequest("hotel", "61083");
        // 2.发送请求
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 3.解析响应结果
        String json = response.getSourceAsString();

        HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
        System.out.println("hotelDoc = " + hotelDoc);
    }

    @Test
    void testDeleteDocumentById() throws IOException {
        // 1.准备Request      // DELETE /hotel/_doc/{id}
        DeleteRequest request = new DeleteRequest("hotel", "61083");
        // 2.发送请求
        client.delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void testUpdateById() throws IOException {
        // 1.准备Request
        UpdateRequest request = new UpdateRequest("hotel", "61083");
        // 2.准备参数
        request.doc(
                "price", "870"
        );
        // 3.发送请求
        client.update(request, RequestOptions.DEFAULT);
    }

    // 批量插入数据
    @Test
    void testBulkRequest() throws IOException {
        // 查询所有的酒店数据
        List<Hotel> list = hotelService.list();

        // 1.准备Request
        BulkRequest request = new BulkRequest();
        // 2.准备参数
        for (Hotel hotel : list) {
            // 2.1.转为HotelDoc
            HotelDoc hotelDoc = new HotelDoc(hotel);
            // 2.2.转json
            String json = JSON.toJSONString(hotelDoc);
            // 2.3.添加请求
            request.add(new IndexRequest("hotel").id(hotel.getId().toString()).source(json, XContentType.JSON));
        }

        // 3.发送请求
        client.bulk(request, RequestOptions.DEFAULT);
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

    // 创建索引
    @Test
    void createIndex() throws IOException {
        CreateIndexRequest hotel = new CreateIndexRequest("hotel");
        hotel.mapping(MAPPING_TEMPLATE, XContentType.JSON);
        client.indices().create(hotel, RequestOptions.DEFAULT);
    }

    // 删除索引
    @Test
    void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    // 获取索引
    @Test
    void getIndex() throws IOException {
        GetIndexRequest hotel = new GetIndexRequest("hotel");
        GetIndexResponse getIndexResponse = client.indices().get(hotel, RequestOptions.DEFAULT);
        String s = getIndexResponse.getMappings().toString();
        System.out.println(s);
    }

    // 向指定索引添加文档;可用于全量更新指定id文档
    @Test
    void addDocIndex() throws IOException {
        Hotel hotel = hotelService.getById(36934);
        HotelDoc hotelDoc = new HotelDoc(hotel);
        IndexRequest indexRequest = new IndexRequest("hotel").id("36934");
        indexRequest.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
        client.index(indexRequest, RequestOptions.DEFAULT);
    }

    // 向指定索引删除指定id文档
    @Test
    void deleteDocIndex() throws IOException {
        DeleteRequest request = new DeleteRequest("hotel", "36934");
        client.delete(request, RequestOptions.DEFAULT);
    }

    // 局部更新指定索引的指定id文档
    @Test
    void updateDocIndex() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("hotel", "36934");
        updateRequest.doc("price", "335");
        updateRequest.doc("city", "杭州");
        updateRequest.doc("brand", "酒店");
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    // 获取指定索引的指定文档id数据
    @Test
    void getDocIndex() throws IOException {
        GetRequest getRequest = new GetRequest("hotel", "36934");
        GetResponse documentFields = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSourceAsString());
    }


    @Test
    void getDocForAgg() {

        try {
            // 准备Request请求
            SearchRequest searchRequest = new SearchRequest("hotel");
            // 准备DSL
            searchRequest.source().size(0);
            searchRequest.source().aggregation(
                    AggregationBuilders.terms("brandAgg")
                            .field("brand")
                            .size(10)
                            .subAggregation(
                                    AggregationBuilders
                                            .stats("ScoreAgg")
                                            .field("score")
                            )
            );
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
            Terms terms = search.getAggregations().get("brandAgg");
            List<? extends Terms.Bucket> buckets = terms.getBuckets();
            HashMap<String, Object> map = new HashMap<>();
            buckets.forEach(item -> {
                map.put("name", item.getKeyAsString());
                System.out.println(item.getKeyAsString());
                System.out.println(item.getAggregations().get("ScoreAgg"));
            });


            handleResponse(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}
