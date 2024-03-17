import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import HomeContainer from './container/HomeContainer';
import BaseLayout from './container/BaseLayout';

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
      <WrappedApp />
    </QueryClientProvider>
  );
};

export default App;
