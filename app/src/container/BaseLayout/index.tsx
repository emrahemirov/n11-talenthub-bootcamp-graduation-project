import { PropsWithChildren } from 'react';
import Header from './Header/Header';

type BaseLayoutProps = PropsWithChildren;

const BaseLayout = ({ children }: BaseLayoutProps) => {
  return (
    <div className='flex gap-4 flex-col'>
      <Header />
      <main>{children}</main>
    </div>
  );
};

export default BaseLayout;
