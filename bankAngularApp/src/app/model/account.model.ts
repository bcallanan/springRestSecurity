export class Account {

  public accountNumber: number;
  public accountType: string;
  public branchAddress: string;
  
  constructor( accountNumber?: number, accountType?: string,
		  branchAddress?: string ) {
	  
        this.accountNumber = accountNumber || 0;
        this.accountType = accountType || '';
        this.branchAddress = branchAddress || '';
  }
}
