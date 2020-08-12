package jp.co.confrage.presentation.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.confrage.presentation.service.DemoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DemoController {

  private final DemoService demoService;

  /**
   * 指定秒数かかるAPI
   *
   * @param seconds1
   * @param seconds2
   * @param seconds3
   * @return
   * @throws InterruptedException
   * @throws ExecutionException
   */
  @RequestMapping(path = "/sleep/{seconds1}/{seconds2}/{seconds3}", method = RequestMethod.GET)
  public ResponseEntity<String> sleep(
      @PathVariable String seconds1, @PathVariable String seconds2, @PathVariable String seconds3)
      throws InterruptedException, ExecutionException {
    long startTime = System.currentTimeMillis();
    CompletableFuture<String> page1 = demoService.async(seconds1);
    CompletableFuture<String> page2 = demoService.async(seconds2);
    CompletableFuture<String> page3 = demoService.async(seconds3);

    CompletableFuture.allOf(page1, page2, page3).join(); // 終了まで待機する

    System.out.println("--> " + page1.get());
    System.out.println("--> " + page2.get());
    System.out.println("--> " + page3.get());
    long endTime = System.currentTimeMillis();
    System.out.println("処理時間：" + (endTime - startTime) + " ms");
    return ResponseEntity.ok("test");
  }
}
