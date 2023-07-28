import React, { useEffect, useState } from 'react';
import { Flex, Text, Box, Button, Wrap, WrapItem, CircularProgress } from '@chakra-ui/react';
import { NavLink } from 'react-router-dom';

function Dashboard() {

  const [communityDetails, setCommunityDetails] = useState(null);
  const [loading, setLoading] = useState(false);
  const userid = localStorage.getItem('userID');
  // const communityDetails = [{
  //     name: "Sample Community",
  //     description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  //     interests : [
  //         { interestName: "Interest 1" },
  //         { interestName: "Interest 2" },
  //         { interestName: "Interest 3" },
  //       ]
  //   },
  //   {
  //     name: "Sample Community",
  //     description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  //     interests :[
  //         { interestName: "Interest 1" },
  //         { interestName: "Interest 2" },
  //         { interestName: "Interest 3" },
  //       ]
  //   },
  //   {
  //     name: "Sample Community",
  //     description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  //     interests :[
  //         { interestName: "Interest 1" },
  //       ]
  //   },
  //   {
  //     name: "Sample Community",
  //     description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  //     interests :[
  //         { interestName: "Interest 1" },
  //         { interestName: "Interest 2" },
  //         { interestName: "Interest 3" },
  //       ]
  //   },{
  //     name: "Sample Community",
  //     description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  //     interests :[
  //         { interestName: "Interest 1" },
  //       ]
  //   },{
  //     name: "Sample Community",
  //     description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  //     interests :[
  //         { interestName: "Interest 3" },
  //       ]
  //   }  
  // ];

  const eventDetails = [
    {
      name: "Event 1",
      description: "This is Event 1 description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
      interests: [
        { interestName: "Event Interest 1" },
        { interestName: "Event Interest 2" },
      ],
    },
    {
      name: "Event 2",
      description: "This is Event 2 description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
      interests: [
        { interestName: "Event Interest 1" },
        { interestName: "Event Interest 3" },
      ],
    },
  ];

  useEffect(() => {
    const fetchData = async () => {
      const getOptions = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem('BearerToken')
        }
      }
      try {
        setLoading(true);
        const response = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/user/${userid}`, getOptions);

        if (response.ok) {
          const responseData = await response.json();

          setCommunityDetails(responseData);

          setLoading(false);
        }
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    loading ? <Flex w="100%" minHeight="90vh" flexDirection="column" alignItems="center" justifyContent="center">
      <CircularProgress isIndeterminate color="teal" />
    </Flex> :
      <Flex direction="column" align="center" p={4}>
        <Box mb={4}>
          <Text fontSize="xl" fontWeight="bold" mb={4}>
            My Communities
          </Text>
          <Box maxH="32vh" overflowY="auto" minW="100vw">
            {communityDetails?.length === 0 ? (
              <>
                <Text fontSize="lg" fontWeight="medium">
                  You are not in any community.
                </Text>
                <NavLink to="/communities">
                  <Button mt={4} colorScheme="teal" variant="solid">
                    Explore All Communities
                  </Button>
                </NavLink>
              </>
            ) : (
              <Flex overflowX="auto" maxW="100%" pb={4} ml="16px" mr="16px">
                <Wrap justify="center" spacing={4} flexWrap="nowrap">
                  {communityDetails?.map((community, i) => (
                    <WrapItem key={i}>
                      <Box p={4} borderWidth="1px" borderRadius="md">
                        <Text fontWeight="bold">{community.name}</Text>
                        <Text>{community.description}</Text>
                        <NavLink to={"/community/" + community.community_id}>
                          <Button mt={4} colorScheme="teal" variant="solid">
                            View Community
                          </Button>
                        </NavLink>
                      </Box>
                    </WrapItem>
                  ))}
                </Wrap>
              </Flex>
            )}
          </Box>
        </Box>

        <Box>
          <Text fontSize="xl" fontWeight="bold" my={4}>
            My Events
          </Text>
          <Box maxH="32vh" overflowY="auto" minW="100vw">
            {eventDetails.length === 0 ? (
              <>
                <Text fontSize="lg" fontWeight="medium">
                  You don't have any event.
                </Text>
                <Button mt={4} colorScheme="teal" variant="solid">
                  Explore Events
                </Button>
              </>
            ) : (
              <Flex overflowX="auto" maxW="100%" pb={4}>
                <Wrap justify="center" spacing={4} flexWrap="nowrap">
                  {eventDetails.map((event, i) => (
                    <WrapItem key={i} w="25%">
                      <Box p={4} borderWidth="1px" borderRadius="md">
                        <Text fontWeight="bold">Event {i + 1}</Text>
                        <Text>{event.description}</Text>
                        <Wrap mt={4}>
                          {event.interests.map((item, key) => (
                            <WrapItem key={key}>
                              <Text fontWeight="medium">{item.interestName}</Text>
                            </WrapItem>
                          ))}
                        </Wrap>
                        <Button mt={4} colorScheme="teal" variant="solid">
                          View Event
                        </Button>
                      </Box>
                    </WrapItem>
                  ))}
                </Wrap>
              </Flex>
            )}
          </Box>
        </Box>
      </Flex>
  );
}

export default Dashboard;
