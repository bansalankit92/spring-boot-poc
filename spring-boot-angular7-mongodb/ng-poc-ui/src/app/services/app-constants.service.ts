import { Injectable } from '@angular/core';

@Injectable()
export class AppConstantsService {

  public static readonly LOCAL_STORAGE_USER = 'dance_user';
  public static readonly LOCAL_STORAGE_PAYMENT = 'dance_payment';
  public static readonly LOCAL_STORAGE_TRANSACTION = 'dance_transaction';
  public static readonly LOCAL_STORAGE_CDNURL = 'dance_cdn_url';
  public static readonly LOCAL_STORAGE_PAYMENT_USER_POST_DTO = 'dance_user_post_dto';
  public static readonly LOCAL_SEARCH_USER = 'search_user';
  public static readonly LOCAL_REFERRAL_ID = 'referral_id';

  public static readonly REGISTRATION_FEES = 0;
  public static readonly ORGINAL_EXTRA_FEES = 20;

  public static readonly ERROR_GENERIC_MESSAGE = 'Some error occurred. Please contact admin team';

  public static readonly DATA_SIZE = 30;

  public static readonly TAGS = [ 'worldsbestdancercup', 'wbdcup2018',
  'dance', 'dancer', 'dancelove' ,
  'dancevideo', 'wbdcup',  'worldsbestdancer'];

  public readonly DASHBOARD_URL = '/';
  public readonly HOME = '/home';

  public readonly LOGIN = '/login';

  public readonly FB_EMBED_URL_START = '//www.facebook.com/plugins/video.php?href=';
  public readonly FB_EMBED_URL_END = '&show_text=1&width=476';

  public readonly SPONSORS = 'sponsor';
  public readonly ADVERTISE = 'advertise';
  public readonly SUPPORT = 'support';

  constructor() {}



}
