export type WithPagination<T> = {
  content: T[];
  hasPrevious: boolean;
  hasNext: boolean;
  totalItems: number;
  totalPages: number;
  pageSize: number;
};
