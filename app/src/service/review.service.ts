import { Review } from '@/model/review.model';
import { QueryParams } from '@/model/query-params.model';
import { RestRequest } from '@/model/rest-request.model';
import { WithPagination } from '@/model/with-pagination.model';
import { baseAxios } from '@/util/baseAxios';

export type FindReviewsByRestaurantIdRequest = RestRequest<
  QueryParams,
  { restaurantId: string }
>;
export type UpdateReviewRequest = RestRequest<
  null,
  null,
  {
    id: number;
    comment?: string;
    rate: number;
  }
>;
export type CreateReviewRequest = RestRequest<
  null,
  null,
  {
    comment?: string;
    rate: number;
    restaurantId: string;
    userId: number;
  }
>;
export type DeleteReviewRequest = RestRequest<null, { id: number }>;

export const findReviewsByRestaurantId = async ({
  requestParams,
  pathVariables,
}: FindReviewsByRestaurantIdRequest) => {
  return baseAxios<WithPagination<Review>>({
    method: 'GET',
    url: `/reviews/by-restaurant-id/${pathVariables?.restaurantId}`,
    params: requestParams,
  });
};

export const createReview = async ({ body }: CreateReviewRequest) => {
  return baseAxios<Review>({
    method: 'POST',
    url: '/reviews',
    data: body,
  });
};

export const updateReview = async ({ body }: UpdateReviewRequest) => {
  return baseAxios<Review>({
    method: 'PUT',
    url: `/reviews/${body?.id}`,
    data: body,
  });
};

export const deleteReview = async ({ pathVariables }: DeleteReviewRequest) => {
  return baseAxios({
    method: 'DELETE',
    url: `/reviews/${pathVariables?.id}`,
  });
};
