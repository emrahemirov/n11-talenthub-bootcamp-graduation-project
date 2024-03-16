export type RestRequest<Q = null, P = null, B = null> = {
  requestParams?: Q;
  pathVariables?: P;
  body?: B;
};
