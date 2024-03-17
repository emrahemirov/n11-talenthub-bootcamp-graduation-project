import { PropsWithChildren } from 'react';
import Header from './Header/Header';
import { Container, Stack } from '@chakra-ui/react';

type BaseLayoutProps = PropsWithChildren;

const BaseLayout = ({ children }: BaseLayoutProps) => {
  return (
    <Stack spacing={4}>
      <Header />
      <Container as={'main'} maxW={'6xl'}>
        {children}
      </Container>
    </Stack>
  );
};

export default BaseLayout;
