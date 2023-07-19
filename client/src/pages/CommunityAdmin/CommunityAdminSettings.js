import { Box, Flex, Skeleton } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import CommunityAdminSideBar from '../../components/SideBar/CommunityAdminSideBar';

function CommunityAdminSettings() {
    const choice = 3;
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
                <CommunityAdminSideBar choice={choice} />
            </Flex>
            <Box flexGrow="6">

                {loading ? <Skeleton /> :
                    <Flex flexDirection="column" justifyContent="start" alignItems="start">
                        Settings of {communityDetails.name} for {userid}
                    </Flex>
                }

            </Box>
            
        </Flex>
    );
}

export default CommunityAdminSettings;