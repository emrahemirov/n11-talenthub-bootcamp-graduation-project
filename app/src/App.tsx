import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import HomeContainer from './container/HomeContainer';

const App = () => {
  const queryClient = new QueryClient();

  return (
    <QueryClientProvider client={queryClient}>
      <HomeContainer />
    </QueryClientProvider>
  );
};

export default App;
