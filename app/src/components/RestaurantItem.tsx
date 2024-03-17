import { calculateDistance } from '@/util/calculate-distance';
import { useEffect, useState } from 'react';
import { GiPathDistance } from 'react-icons/gi';
import { Restaurant } from '@/model/restaurant.model';
import { Card, CardBody, Flex, Image, Stack, Text } from '@chakra-ui/react';
import RestaurantReviewList from './RestaurantReviewList/RestaurantReviewList';

type RestaurantItemProps = { restaurant: Restaurant };

const RestaurantItem = ({ restaurant }: RestaurantItemProps) => {
  const [lat, lng] = restaurant.geo.split(',');

  const [userLocation, setUserLocation] = useState<null | {
    lat: number;
    lng: number;
  }>(null);

  useEffect(() => {
    navigator.geolocation.getCurrentPosition((position) => {
      setUserLocation({
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      });
    });
  }, []);

  return (
    <Card key={restaurant.id}>
      <CardBody p={0} position={'relative'}>
        <Image
          objectFit={'cover'}
          w={'xs'}
          h={48}
          overflow={'hidden'}
          rounded={'lg'}
          src={restaurant.imageSrc}
        />
        <Flex
          color={'white'}
          justify={'space-between'}
          position={'absolute'}
          bottom={-8}
          left={'50%'}
          transform={'translateX(-50%)'}
          w={'95%'}
          h={24}
          bgColor={'#5d3ebccf'}
          rounded={'lg'}
          my={2}
          p={4}
        >
          <Stack>
            <Text noOfLines={1} fontWeight={'bold'}>
              {restaurant.name}
            </Text>
            {userLocation ? (
              <Flex gap={2} align={'center'} mt={'auto'}>
                <GiPathDistance />
                {calculateDistance(
                  +lat,
                  +lng,
                  userLocation?.lat,
                  userLocation?.lng,
                ).toFixed(2)}
                km
              </Flex>
            ) : null}
          </Stack>
          <RestaurantReviewList restaurant={restaurant} />
        </Flex>
      </CardBody>
    </Card>
  );
};

export default RestaurantItem;
