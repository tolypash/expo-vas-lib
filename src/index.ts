import ExpoVasLibModule from "./ExpoVasLibModule";
import { ResponseData } from "./ExpoVasLib.types";

export function initialise() {
  return ExpoVasLibModule.initialise();
}

export async function doTransaction(
  amount: number,
  orderNumber: string,
  needAppPrinted: boolean
): Promise<ResponseData> {
  const jsonString = await ExpoVasLibModule.doTransaction(
    amount,
    orderNumber,
    needAppPrinted
  );

  return JSON.parse(jsonString) as ResponseData;
}

export * from "./ExpoVasLib.types";
