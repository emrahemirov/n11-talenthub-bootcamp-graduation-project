import {
  DeleteReviewRequest,
  FindReviewsByRestaurantIdRequest,
  UpdateReviewRequest,
  deleteReview,
  findReviewsByRestaurantId,
  updateReview,
} from '@/service/review.service';
import { CreateReviewRequest, createReview } from '@/service/review.service';
import {
  useInfiniteQuery,
  useMutation,
  useQueryClient,
} from '@tanstack/react-query';

export const REVIEW_QUERY_KEYS = {
  reviews: ['REVIEWS'],
  restaurantsByReviewId({ pathVariables }: FindReviewsByRestaurantIdRequest) {
    return [...REVIEW_QUERY_KEYS.reviews, pathVariables];
  },
} as const;

export const useFindReviewsByRestaurantIdInfiniteQuery = ({
  enabled,
  pathVariables,
}: FindReviewsByRestaurantIdRequest & { enabled: boolean }) => {
  return useInfiniteQuery({
    queryKey: REVIEW_QUERY_KEYS.restaurantsByReviewId({
      pathVariables,
    }),
    queryFn: ({ pageParam }) =>
      findReviewsByRestaurantId({
        requestParams: { page: pageParam, size: 20 },
        pathVariables,
      }),
    initialPageParam: 0,
    getNextPageParam: (lastPage, pages) =>
      lastPage.data.hasNext ? pages.length : null,
    enabled,
  });
};

export const useCreateReviewMutation = ({ body }: CreateReviewRequest) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: () => createReview({ body }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: REVIEW_QUERY_KEYS.reviews,
      });
    },
  });
};

export const useUpdateRestaurantMutation = ({ body }: UpdateReviewRequest) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: () => updateReview({ body }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: REVIEW_QUERY_KEYS.reviews,
      });
    },
  });
};

export const useDeleteReviewMutation = ({
  pathVariables,
}: DeleteReviewRequest) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: () => deleteReview({ pathVariables }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: REVIEW_QUERY_KEYS.reviews,
      });
    },
  });
};
