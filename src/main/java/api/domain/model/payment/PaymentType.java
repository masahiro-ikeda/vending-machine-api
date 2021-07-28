package api.domain.model.payment;

/**
 * 支払いタイプ.
 */
public enum PaymentType {
  PAY, // 支払
  REPAY, // 返金
  CHANGE; // おつり
}
