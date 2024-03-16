import {
  DeleteUserRequest,
  UpdateUserRequest,
  deleteUser,
  updateUser,
} from '@/service/user.service';
import { CreateUserRequest, createUser } from '@/service/user.service';
import { useAuthStore } from '@/store/auth.store';
import { useMutation } from '@tanstack/react-query';

export const useCreateUserMutation = ({ body }: CreateUserRequest) => {
  const { updateUser } = useAuthStore();

  return useMutation({
    mutationFn: () => createUser({ body }),
    onSettled(data) {
      data?.data && updateUser({ user: data?.data });
    },
  });
};

export const useUpdateRestaurantMutation = ({ body }: UpdateUserRequest) => {
  const { updateUser: updateAuthUser } = useAuthStore();

  return useMutation({
    mutationFn: () => updateUser({ body }),
    onSettled(data) {
      data?.data && updateAuthUser({ user: data?.data });
    },
  });
};

export const useDeleteUserMutation = ({ pathVariables }: DeleteUserRequest) => {
  const { logout } = useAuthStore();

  return useMutation({
    mutationFn: () => deleteUser({ pathVariables }),
    onSettled() {
      logout();
    },
  });
};
