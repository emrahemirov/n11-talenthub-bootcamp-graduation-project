import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import HomeContainer from './container/HomeContainer';
import BaseLayout from './container/BaseLayout';
import { ChakraProvider } from '@chakra-ui/react';
import { theme } from './styles/theme';

const WrappedApp = () => {
  return (
    <BaseLayout>
      <HomeContainer />
    </BaseLayout>
  );
};

const App = () => {
  const queryClient = new QueryClient({
    defaultOptions: { queries: { retry: false } },
  });

  return (
    <QueryClientProvider client={queryClient}>
      <ChakraProvider theme={theme}>
        <WrappedApp />
      </ChakraProvider>
    </QueryClientProvider>
  );
};

export default App;
