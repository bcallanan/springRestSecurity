export class Loans {

  public loanNumber: number;
  public startDate: Date;
  public loanType: string;
  public totalLoanValue: number;
  public amountPaid: number;
  public outstandingBalance: number;
  
  constructor( loanNumber?: number, startDate?: Date, loanType?: string,
		  totalLoanValue?: number, amountPaid?: number, outstandingBalance?: number){
        this.loanNumber = loanNumber || 0;
        this.startDate = startDate!;
        this.loanType = loanType || "";
        this.totalLoanValue = totalLoanValue || 0;
        this.amountPaid = amountPaid || 0;
        this.outstandingBalance = outstandingBalance || 0;
  }
}
