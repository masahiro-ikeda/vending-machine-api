package api.domain.model.payments.payment;

/**
 * 支払いタイプ.
 */
public enum PaymentType {
  PAY, // 支払
  REPAY, // 返金
  PURCHASE, // 購入
  CHANGE // おつり
}
