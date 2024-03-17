import {
  DeleteUserRequest,
  FindUserByIdRequest,
  UpdateUserRequest,
  deleteUser,
  findUserById,
  updateUser,
} from '@/service/user.service';
import { CreateUserRequest, createUser } from '@/service/user.service';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';

export const USER_QUERY_KEYS = {
  user: ['USER'],
  userById({ id }: { id?: number }) {
    return ['USER', id];
  },
};

export const useFinUerByIdQuery = ({ pathVariables }: FindUserByIdRequest) => {
  return useQuery({
    queryFn: () => findUserById({ pathVariables }),
    queryKey: USER_QUERY_KEYS.userById({ id: pathVariables?.id }),
  });
};

export const useCreateUserMutation = ({ body }: CreateUserRequest) => {
  return useMutation({
    mutationFn: () => createUser({ body }),
  });
};

export const useUpdateRestaurantMutation = ({ body }: UpdateUserRequest) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: () => updateUser({ body }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: USER_QUERY_KEYS.userById({ id: body?.id }),
      });
    },
  });
};

export const useDeleteUserMutation = ({ pathVariables }: DeleteUserRequest) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: () => deleteUser({ pathVariables }),
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: USER_QUERY_KEYS.userById({ id: pathVariables?.id }),
      });
    },
  });
};
