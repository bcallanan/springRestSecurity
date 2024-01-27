export class Cards {

  public cardNumber: string;
  public cardType: string;
  public cardLimit: number;
  public amountOutstanding: number;
  public amountAvailable: number;
  
  constructor ( cardNumber?: string, cardType?: string, cardLimit?: number,
     amountOutstanding?: number, amountAvailable?: number ) {
     
      this.cardNumber = cardNumber || "";
      this.cardType = cardType || "";
      this.cardLimit = cardLimit || 0;
      this.amountOutstanding = amountOutstanding || 0;
      this.amountAvailable = amountAvailable || 0;
  }
}
