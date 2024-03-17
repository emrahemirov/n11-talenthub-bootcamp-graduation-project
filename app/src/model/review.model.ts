import { User } from './user.model';

export type Review = {
  id: number;
  comment: string;
  rate: number;
  createdAt: string;
  updatedAt: string;
  user: User;
};
