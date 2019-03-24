import { TestBed, inject } from '@angular/core/testing';

import { AppConstantsService } from './app-constants.service';

describe('AppConstantsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AppConstantsService]
    });
  });

  it('should ...', inject([AppConstantsService], (service: AppConstantsService) => {
    expect(service).toBeTruthy();
  }));
});
