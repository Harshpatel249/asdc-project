import { Box, Flex, Skeleton, FormControl,FormLabel, Heading, Input, Select, Textarea } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { Form, useParams } from 'react-router-dom';
import CommunityMembers from '../../components/SideBar/CommunityMembers';
import CommunitySideBar from '../../components/SideBar/CommunitySideBar';

function CommunityPosts() {
    const choice = 2;
    let { cid } = useParams();
    const [communityDetails, setCommunityDetails] = useState();
    const [loading, setLoading] = useState(true);
    const userid = 2;


    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}`);

        
                if (response.ok ) {
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
    }, [cid]); 


    return (
        <Flex>
            <Flex flexGrow="1" justifyContent="center" alignItems="center" h="69vh">
                <CommunitySideBar choice={choice} />
               
            </Flex>
            <Box flexGrow="6">
//                <form>
//                <FormControl id="name" marginTop="16px">
//                        <FormLabel>Name</FormLabel>
//                        <Input
//                            type="text"
//                            placeholder="Enter Post Title"
//                        />
//                </FormControl>
//
//                </form>

                {loading ? <Skeleton /> :
                    <Flex flexDirection="column" justifyContent="start" alignItems="start">
                        POSTS Page of {communityDetails.name} for {userid}
                    </Flex>
                }
            </Box>
            <Flex flexGrow="1">
                <CommunityMembers />
            </Flex>
        </Flex>
    );
}

export default CommunityPosts;