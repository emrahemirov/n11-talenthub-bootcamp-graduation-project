import {
  DeleteUserRequest,
  UpdateUserRequest,
  deleteUser,
  findUserById,
  updateUser,
} from '@/service/user.service';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';

export const USER_QUERY_KEYS = {
  user: ['USER'],
};

export const useFindUserByIdQuery = () => {
  return useQuery({
    queryFn: () => findUserById({ pathVariables: { id: 1 } }),
    queryKey: USER_QUERY_KEYS.user,
  });
};

export const useUpdateUserMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ body }: UpdateUserRequest) => updateUser({ body }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: USER_QUERY_KEYS.user,
      });
    },
  });
};

export const useDeleteUserMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ pathVariables }: DeleteUserRequest) =>
      deleteUser({ pathVariables }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: USER_QUERY_KEYS.user,
      });
    },
  });
};
