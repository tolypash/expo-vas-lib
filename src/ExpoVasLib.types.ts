export type ResponseData = {
  acquirer: string;
  additionalAmount: string;
  amount: number;
  amountStr: string;
  authCode?: string;
  batchNumber?: string;
  cardNumber?: string;
  panLast4Digits?: string;
  cardProduct?: string;
  cardType: string;
  cvmResults: string;
  entryMode: string;
  expirationDate?: string;
  interfaceId: string;
  localCurrency: string;
  mcc: string;
  merchantAddress: string;
  merchantName: string;
  merchantNumber: string;
  merchantPostalCode: string;
  merchantVatNuber: string;
  operatorNumber: string;
  packageName: string;
  posVersion: string;
  referenceNumber: string;
  responseCode: number;
  responseCodeThirtyNine?: string;
  responseMessage: string;
  responseMessageThirtyNine?: string;
  terminalNumber: string;
  voided: boolean;
  voucherNumber: string;
  orderNumber: string;
};
