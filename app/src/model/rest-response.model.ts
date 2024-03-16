export type RestResponseWithData<D> = {
  data: D;
  error: null;
  responseDate: string;
  success: boolean;
};

export type RestResponseWithError<E> = {
  data: null;
  error: E;
  responseDate: string;
  success: boolean;
};
