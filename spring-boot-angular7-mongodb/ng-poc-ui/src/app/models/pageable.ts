
export class Pageable {
    content: any;
    pageable: {
      sort: {
        unsorted: boolean,
        sorted: boolean
      },
      pageSize: number,
      pageNumber: number,
      offset: number,
      paged: boolean,
      unpaged: boolean
    };
    last: boolean;
    totalPages: number;
    totalElements: number;
    numberOfElements: number;
    first: boolean;
    sort: {
      unsorted: boolean,
      sorted: boolean
    };
    size: number;
    number: number;
  }
