import { Review } from '@/model/review.model';
import { QueryParams } from '@/model/query-params.model';
import { RestRequest } from '@/model/rest-request.model';
import { WithPagination } from '@/model/with-pagination.model';
import { baseAxios } from '@/util/baseAxios';

export type FindAllReviewsRequest = RestRequest<QueryParams>;
export type FindReviewByIdRequest = RestRequest<null, { id: number }>;
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
    id: number;
    comment?: string;
    rate: number;
  }
>;
export type DeleteReviewRequest = RestRequest<null, { id: number }>;

export const findAllReviews = async ({
  requestParams,
}: FindAllReviewsRequest) => {
  return baseAxios<WithPagination<Review>>({
    method: 'GET',
    url: '/reviews',
    params: requestParams,
  });
};

export const findReviewById = async ({
  pathVariables,
}: FindReviewByIdRequest) => {
  return baseAxios<Review>({
    method: 'GET',
    url: `/reviews/${pathVariables.id}`,
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
    url: `/reviews/${body.id}`,
    data: body,
  });
};

export const deleteReview = async ({ pathVariables }: DeleteReviewRequest) => {
  return baseAxios({
    method: 'DELETE',
    url: `/reviews/${pathVariables.id}`,
  });
};
