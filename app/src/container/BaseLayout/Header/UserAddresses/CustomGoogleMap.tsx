import { GoogleMap, type GoogleMapProps } from '@react-google-maps/api';
import { IoMdPin } from 'react-icons/io';
import { Box } from '@chakra-ui/react';

const CustomGoogleMap = (props: GoogleMapProps) => {
  return (
    <Box sx={{ minHeight: '50vh', width: '100%', position: 'relative' }}>
      <GoogleMap
        mapContainerStyle={{ minHeight: 'inherit', width: '100%' }}
        zoom={10}
        options={{
          fullscreenControl: false,
          mapTypeControl: false,
          streetViewControl: false,
        }}
        {...props}
      />

      <Box
        as={IoMdPin}
        size={32}
        sx={{
          position: 'absolute',
          top: '50%',
          left: '50%',
          color: 'orangered',
          transform: 'translate(-50%,-50%)',
        }}
      />
    </Box>
  );
};

export default CustomGoogleMap;
