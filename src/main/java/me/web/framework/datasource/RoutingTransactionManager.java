package me.web.framework.datasource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.Assert;

/**
 * 根据给定的路由规则来路由到合适的tx类
 * 
 * @see RoutingContextHolder
 */
public class RoutingTransactionManager implements PlatformTransactionManager {
  private Map<Object, PlatformTransactionManager> targetTransactionManagers =
      new HashMap<Object, PlatformTransactionManager>();

  /**
   * 根据给定的规则获取指定的tx
   * 
   * @return
   */
  protected PlatformTransactionManager getTargetTransactionManager() {
    Object context = RoutingContextHolder.getContext();
    Assert.notNull(context,"context is unavailable from RoutingContextHolder !");
    return targetTransactionManagers.get(context);
  }

  public void setTargetTransactionManagers(Map<Object, PlatformTransactionManager> targetTransactionManagers) {
    this.targetTransactionManagers = targetTransactionManagers;
  }

  public void commit(TransactionStatus status) throws TransactionException {
    getTargetTransactionManager().commit(status);
  }

  public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
    return getTargetTransactionManager().getTransaction(definition);
  }

  public void rollback(TransactionStatus status) throws TransactionException {
    getTargetTransactionManager().rollback(status);
  }
}
