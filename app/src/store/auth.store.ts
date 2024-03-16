import { User } from '@/model/user.model';
import { FindUserByIdRequest, findUserById } from '@/service/user.service';
import { useQueryClient } from '@tanstack/react-query';
import { create } from 'zustand';

type AuthStore = {
  user?: User;
  login: ({ pathVariables }: FindUserByIdRequest) => Promise<void>;
  logout: () => void;
  updateUser: ({ user }: { user: User }) => void;
};

export const useAuthStore = create<AuthStore>((set) => {
  const queryClient = useQueryClient();

  return {
    user: undefined,
    login: async ({ pathVariables }: FindUserByIdRequest) => {
      set({
        user: (await findUserById({ pathVariables })).data,
      });
    },
    logout: () => {
      set({
        user: undefined,
      });

      queryClient.resetQueries();
    },
    updateUser: ({ user }: { user: User }) => {
      set({
        user,
      });
    },
  };
});
