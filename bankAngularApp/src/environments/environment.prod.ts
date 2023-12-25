export const environment = {
  production: true,
  rooturl : 'http://192.168.0.29:7075',
  redirectUrl: 'http://192.168.10.8:4200',

  getRedirectUrl() : string {
    return this.redirectUrl;
  },
  setRedirectUrl( redirectUrl: string ) : void {
    this.redirectUrl = redirectUrl;
  },
  appFooter: '� BC Bank, Member FDIC - Production'
};
