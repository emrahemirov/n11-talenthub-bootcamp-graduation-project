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

export const useCreateReviewMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ body }: CreateReviewRequest) => createReview({ body }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: REVIEW_QUERY_KEYS.reviews,
      });
    },
  });
};

export const useUpdateReviewMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ body }: UpdateReviewRequest) => updateReview({ body }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: REVIEW_QUERY_KEYS.reviews,
      });
    },
  });
};

export const useDeleteReviewMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ pathVariables }: DeleteReviewRequest) =>
      deleteReview({ pathVariables }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: REVIEW_QUERY_KEYS.reviews,
      });
    },
  });
};
