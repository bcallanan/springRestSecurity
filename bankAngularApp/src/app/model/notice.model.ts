export class Notice {

  public noticeSummary: string;
  public noticeDetails: string;
  
  constructor( noticeSummary?: string, noticeDetails?: string ) {

        this.noticeSummary = noticeSummary !;
        this.noticeDetails = noticeDetails ! ;
  }
}
