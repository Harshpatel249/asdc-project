import { Box, Flex, Skeleton} from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import CommunityMembers from '../../components/SideBar/CommunityMembers';
import CommunitySideBar from '../../components/SideBar/CommunitySideBar';

function CommunityPosts() {
    const choice = 2;
    let { cid } = useParams();
    const [communityDetails, setCommunityDetails] = useState();
    const [loading, setLoading] = useState(true);
    const [postData, setPostData] = useState([]);
    const userid = 2;


    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("http://localhost:8080/posts/");
                const data = await response.json();
                // setCommunityDetails(responseData);
                setPostData(data);
                console.log("postData: ",postData);
                setLoading(false);
                
            } catch (error) {
                console.error("Error fetching posts:", error);
                setLoading(false);
            }
        };

        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []); 

    if (loading) {
        return <div>Loading...</div>;
      }


    return (
        <Flex>
            <Flex flexGrow="1" justifyContent="center" alignItems="center" h="69vh">
                <CommunitySideBar choice={choice} />
               
            </Flex>
            <div>
                <h1>Post Data</h1>
                <ul>
                    {postData.map((post) => (
                    <li key={post.postId}>
                        <h2>{post.postTitle}</h2>
                        <p>{post.description}</p>
                        {/* Render other post data as needed */}
                    </li>
                    ))}
                </ul>
            </div>
            <Box flexGrow="6">

                {loading ? <Skeleton /> :
                    <Flex flexDirection="column" justifyContent="start" alignItems="start">
                        POSTS Page of abc for {userid}
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