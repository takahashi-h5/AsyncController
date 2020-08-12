package jp.co.confrage.presentation.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

  /**
   * 非同期処理
   *
   * @param seconds
   * @return
   * @throws InterruptedException
   */
  @Async
  public CompletableFuture<String> async(String seconds) throws InterruptedException {
    Thread.sleep(Long.valueOf(seconds) * 1000L);
    return CompletableFuture.completedFuture(seconds + "秒かかる処理");
  }
}
