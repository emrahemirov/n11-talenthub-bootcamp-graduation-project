import { RestResponseWithData } from '@/model/rest-response.model';
import axios, { AxiosRequestConfig } from 'axios';

export const instance = axios.create({
  baseURL: 'http://localhost:9191/api',
});

instance.interceptors.response.use(
  (response) => response.data,
  async (error) => {
    return Promise.reject(error.response?.data?.error);
  },
);

/**
 * Replaces main `axios` instance with the custom one.
 *
 * @param cfg - Axios configuration object.
 * @returns A promise object of a response of the HTTP request with the 'data' object already
 * destructured.
 * @example
 * const login = () => {
 *    axios<ResponseModel>({ method: 'POST', url: '/url', data: { ... } })
 *      .then((user) => {})
 *      .catch((err) => {});
 * }; */
export const baseAxios = <D>(cfg: AxiosRequestConfig) =>
  instance.request<unknown, RestResponseWithData<D>>(cfg);
