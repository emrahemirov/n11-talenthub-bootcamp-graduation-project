import { defineStyleConfig, extendTheme } from '@chakra-ui/react';

export const colors = {
  n11: {
    50: '#eff0fe',
    100: '#e2e3fd',
    200: '#cbccfa',
    300: '#ababf6',
    400: '#9289f0',
    500: '#7e6ce8',
    600: '#7050db',
    700: '#5d3ebc',
    800: '#4f379c',
    900: '#42337c',
    950: '#281e48',
  },
};

const components = {
  Button: defineStyleConfig({
    defaultProps: { colorScheme: 'n11' },
  }),

  Input: defineStyleConfig({
    defaultProps: { colorScheme: 'n11' },
  }),
  Switch: defineStyleConfig({
    defaultProps: { colorScheme: 'n11' },
  }),
  Checkbox: defineStyleConfig({
    defaultProps: { colorScheme: 'n11' },
  }),
  Radio: defineStyleConfig({
    defaultProps: { colorScheme: 'n11' },
  }),
  Tooltip: defineStyleConfig({
    baseStyle: { borderRadius: 2, p: 2 },
  }),
  Progress: defineStyleConfig({
    defaultProps: { colorScheme: 'n11' },
  }),
};

export const theme = extendTheme({
  colors,
  components,
});
