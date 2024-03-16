import { QueryParams } from '@/model/query-params.model';
import { RestRequest } from '@/model/rest-request.model';
import { Restaurant } from '@/model/restaurant.model';
import { WithPagination } from '@/model/with-pagination.model';
import { baseAxios } from '@/util/baseAxios';

export type FindAllRestaurantsRequest = RestRequest<QueryParams>;
export type FindRecommendedRestaurantsRequest = RestRequest<
  Partial<{ userId: number }>
>;
export type UpdateRestaurantRequest = RestRequest<
  null,
  null,
  {
    id: string;
    name: string;
    latitude: string;
    longitude: string;
  }
>;
export type CreateRestaurantRequest = RestRequest<
  null,
  null,
  {
    name: string;
    latitude: string;
    longitude: string;
  }
>;
export type DeleteRestaurantRequest = RestRequest<null, { id: string }>;

export const findAllRestaurants = async ({
  requestParams,
}: FindAllRestaurantsRequest) => {
  return baseAxios<WithPagination<Restaurant>>({
    method: 'GET',
    url: '/restaurants',
    params: requestParams,
  });
};

export const findRecommendedRestaurants = async ({
  requestParams,
}: FindRecommendedRestaurantsRequest) => {
  return baseAxios<Restaurant>({
    method: 'GET',
    url: '/restaurants/recommended',
    params: requestParams,
  });
};
