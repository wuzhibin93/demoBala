package ElasticSearchLocalTest;

import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出日志线程
 *
 * @author changgq
 */
@AllArgsConstructor
public class ExportLogThread{
    @Test
    public void test1(){
        String startTime = "2:30:00";
        String endTime = "5:35:00";
        String[] strs = startTime.split(":");
        String[] str2 = endTime.split(":");
        int total = Integer.parseInt(str2[0])-Integer.parseInt(strs[0]);
        if (!str2[1].equals("00")){
            total+=1;
        }
        //System.out.println(total);
        Map<String,String> map = new HashMap<>();
        Integer endT = Integer.parseInt(strs[0]);
        for (int i = 0; i<total; i++){
            if (i == 0) {
                map.put(startTime, endT++ + ":59:59");
            }else if (i != 0 && i <total-1){
                map.put(Integer.parseInt(strs[0])+i+":00:00",endT + ":59:59");
            }else {
                map.put(Integer.parseInt(strs[0])+i+":00:00",endTime);
            }
        }
        //System.out.println(map);

        List<Map<String,String>> list = new ArrayList<>();
        list.add(map);
        int success = 0;
        float percent = 0f;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            success+=1;
            System.out.println(entry.getKey() + "------" + entry.getValue());
            //System.out.println(success+"---success---");
            percent = success / (float)total *100;
            //System.out.println(percent+"---percent---");
        }

    }

    /*@Override
    public void run() {
        float percent = 0;
        try {
            LogDownload ld = new LogDownload();
            String startTime = request.getStartTime();
            String endTime = request.getEndTime();
            String[] strs = startTime.split(":");
            String[] str2 = endTime.split(":");
            List<String> downloads = new ArrayList<>();
            int total = Integer.parseInt(str2[0])-Integer.parseInt(strs[0]);
            if (!str2[1].equals("00")){
                total+=1;
            }
            Integer endT = Integer.parseInt(strs[0]);
            for (int i = 0; i < total; i++) {
                endT++;
                if (i == 0){
                    request.setStartTime(startTime);
                    request.setEndTime(endT+":00:00");
                    writeExcel(request,indexPrefix,downloads,percent);
                }else if (i!=0 && i<total-1){
                    request.setStartTime(endT-1+":00:01");
                    request.setEndTime(endT+":00:00");
                    writeExcel(request,indexPrefix,downloads,percent);
                }else {
                    request.setStartTime(endT-1+":00:01");
                    request.setEndTime(endTime);
                    writeExcel(request,indexPrefix,downloads,percent);
                }
            }
            //boolean result = clearScrollResponse.isSucceeded();
            String timestamp = String.valueOf(new Date().getTime());
            String zipPath = esConfig.getTmpDownload() + "zip/" + timestamp + "/";
            String zipName = timestamp + ".zip";
            String zipUrl = zipPath + zipName;
            new ZipCompressor(zipUrl).compress(downloads.toArray(new String[downloads.size()]));
            File file = new File(zipUrl);
            ld.setFile_size(getZipSize(file));
            ld.setPercent(percent * 100);
            logDownloadRepository.saveOrUpdate(ld);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private long getZipSize(File file) {
        long size = 0;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            FileChannel channel = fileIn.getChannel();
            try {
                size = channel.size();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return size;
    }
    public void writeExcel(LogSearchRequest request,String indexPrefix,List<String> downloads,float percent) throws Exception{
        //float percent = 0;
        long success = 0;
        LogDownload ld = new LogDownload();
        QueryBuilder qb = logSearchRepository.createQueryBuilder(request, indexPrefix);
        ld.setId(IDUtils.genIdx32());
        ld.setCreate_at(DateUtils.datetime2string(new Date()));
        ld.setFile_name(request.getFileName());
        File excel = new File(esConfig.getTmpDownload() + "/" + ld.getId() + ".xlsx");
        ld.setFile_path(excel.getPath());
        downloads.add(excel.getPath());//将文件路径添加到待压缩集合中
        ld.setLog_type(indexPrefix);
        SearchRequest searchRequest = new SearchRequest(indexPrefix + "-filebeat-*");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(qb);
        searchSourceBuilder.size(1000);
        searchSourceBuilder.sort("create_at", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));
        SearchResponse searchResponse = esClient.search(searchRequest);
        String scrollId = searchResponse.getScrollId();
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Map<String, Object>> listData = new ArrayList<>();
        while (hits != null && hits.length > 0) {
            // 写文件
            for (SearchHit sh : hits) {
                listData.add(sh.getSourceAsMap());
            }
            createExcel(excel.getPath(), getHeaders(indexPrefix), listData);
            success += hits.length;
            percent = success / searchResponse.getHits().getTotalHits();
            // 查询下一页数据
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(TimeValue.timeValueSeconds(30));
            SearchResponse searchScrollResponse = esClient.searchScroll(scrollRequest);
            scrollId = searchScrollResponse.getScrollId();
            hits = searchScrollResponse.getHits().getHits();
        }
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = esClient.clearScroll(clearScrollRequest);
    }*/
}
