import React from 'react';
import { Flex, Text, Box, Button, Wrap, WrapItem } from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom';

function Dashboard() {
  const navigate = useNavigate();
  
const communityDetails = [{
    name: "Sample Community",
    description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    interests : [
        { interestName: "Interest 1" },
        { interestName: "Interest 2" },
        { interestName: "Interest 3" },
      ]
  },
  {
    name: "Sample Community",
    description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    interests :[
        { interestName: "Interest 1" },
        { interestName: "Interest 2" },
        { interestName: "Interest 3" },
      ]
  },
  {
    name: "Sample Community",
    description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    interests :[
        { interestName: "Interest 1" },
      ]
  },
  {
    name: "Sample Community",
    description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    interests :[
        { interestName: "Interest 1" },
        { interestName: "Interest 2" },
        { interestName: "Interest 3" },
      ]
  },{
    name: "Sample Community",
    description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    interests :[
        { interestName: "Interest 1" },
      ]
  },{
    name: "Sample Community",
    description: "This is a sample community description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    interests :[
        { interestName: "Interest 3" },
      ]
  }  
];

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

  const handleAllEvents = (e) => {
    navigate('/evetn-list');
  }

  return (
    <Flex direction="column" align="center" p={4}>
      <Box mb={4}>
        <Text fontSize="xl" fontWeight="bold" mb={4}>
          My Communities
        </Text>
        <Box maxH="32vh" overflowY="auto" minW="100vw">
          {communityDetails.length === 0 ? (
            <>
              <Text fontSize="lg" fontWeight="medium">
                You are not in any community.
              </Text>
              <Button mt={4} colorScheme="teal" variant="solid">
                Explore All Communities
              </Button>
            </>
          ) : (
            <Flex overflowX="auto" maxW="100%" pb={4}>
              <Wrap justify="center" spacing={4} flexWrap="nowrap">
                {communityDetails.map((community, i) => (
                  <WrapItem key={i} w="25%">
                    <Box p={4} borderWidth="1px" borderRadius="md">
                      <Text fontWeight="bold">Community {i + 1}</Text>
                      <Text>{community.description}</Text>
                      <Wrap mt={4}>
                        {community.interests.map((item, key) => (
                          <WrapItem key={key}>
                            <Text fontWeight="medium">{item.interestName}</Text>
                          </WrapItem>
                        ))}
                      </Wrap>
                      <Button mt={4} colorScheme="teal" variant="solid">
                        View Community
                      </Button>
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
              <Button mt={4} colorScheme="teal" variant="solid" onClick={handleAllEvents}>
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
