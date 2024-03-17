import * as React from 'react';
import { useJsApiLoader, type LoadScriptProps } from '@react-google-maps/api';
import { HiOutlineMapPin } from 'react-icons/hi2';

import CustomGoogleMap from './CustomGoogleMap';
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  useDisclosure,
  Button,
} from '@chakra-ui/react';
const libraries: LoadScriptProps['libraries'] = ['places'];
export interface Geo {
  latitude: number;
  longitude: number;
}

interface GeoMapProps {
  initialCenter?: {
    latitude: string;
    longitude: string;
  };
  onCenterChange?: (geo: { latitude: string; longitude: string }) => void;
}

const MapModal = ({ initialCenter, onCenterChange }: GeoMapProps) => {
  const { isLoaded } = useJsApiLoader({
    id: 'google-map-script',
    googleMapsApiKey: '',
    libraries,
  });

  const { isOpen, onOpen, onClose } = useDisclosure();
  const [map, setMap] = React.useState<google.maps.Map | null>(null);
  const [center, setCenter] = React.useState<Geo>(
    initialCenter?.latitude
      ? {
          latitude: parseFloat(initialCenter.latitude),
          longitude: parseFloat(initialCenter.longitude),
        }
      : {
          latitude: 38.430925,
          longitude: 27.148196,
        },
  );

  const onLoad = (map: google.maps.Map) => setMap(map);
  const onUnmount = () => setMap(null);

  const handleChange = () => {
    const newCenter = map?.getCenter();
    if (!newCenter) return;

    setCenter({
      latitude: newCenter.lat() as number,
      longitude: newCenter.lng() as number,
    });

    isLoaded &&
      onCenterChange?.({
        latitude: newCenter.lat().toString(),
        longitude: newCenter.lng().toString(),
      });
  };

  return (
    <>
      <Button
        variant={'outline'}
        w={'fit-content'}
        aria-label='map'
        leftIcon={<HiOutlineMapPin />}
        onClick={onOpen}
      >
        Konum seç
      </Button>
      {isLoaded ? (
        <Modal isOpen={isOpen} onClose={onClose}>
          <ModalOverlay />
          <ModalContent>
            <ModalHeader></ModalHeader>
            <ModalCloseButton />
            <ModalBody>
              <CustomGoogleMap
                onLoad={onLoad}
                onUnmount={onUnmount}
                center={{ lat: center.latitude, lng: center.longitude }}
                onDragEnd={handleChange}
                onZoomChanged={handleChange}
              />
            </ModalBody>

            <ModalFooter></ModalFooter>
          </ModalContent>
        </Modal>
      ) : null}
    </>
  );
};

export default MapModal;
