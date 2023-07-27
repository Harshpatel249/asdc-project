import { CircularProgress, Flex } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import CommunityMembers from '../../components/SideBar/CommunityMembers';
import CommunitySideBar from '../../components/SideBar/CommunitySideBar';

function CommunityPosts() {
    const choice = 1;
    let { cid } = useParams();
    const [communityDetails, setCommunityDetails] = useState();
    const [loading, setLoading] = useState(true);
    const userid = localStorage.getItem("userID");


    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}`);

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
    }, [cid]);


    return (
        <Flex minH="90vh">
            <Flex w="15%" justifyContent="center" alignItems="center" minH="90vh">
                <CommunitySideBar selectedTab={choice} />
            </Flex>
            <Flex w="65%">

                {loading ? <Flex w="100%" minHeight="90vh" flexDirection="column" alignItems="center" justifyContent="center">
                    <CircularProgress isIndeterminate color="teal" />
                </Flex> :
                    <Flex flexDirection="column" justifyContent="start" alignItems="start">
                        POSTS Page of {communityDetails.name} for {userid}
                    </Flex>
                }
            </Flex>
            <Flex w="20%">
                <CommunityMembers />
            </Flex>
        </Flex>
    );
}

export default CommunityPosts;