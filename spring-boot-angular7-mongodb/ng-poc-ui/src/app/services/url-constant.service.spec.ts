import { TestBed, inject } from '@angular/core/testing';

import { UrlConstantService } from './url-constant.service';

describe('UrlConstantService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UrlConstantService]
    });
  });

  it('should ...', inject([UrlConstantService], (service: UrlConstantService) => {
    expect(service).toBeTruthy();
  }));
});
