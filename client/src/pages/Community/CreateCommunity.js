import React, { useState, useEffect } from 'react';
import { Box, Heading, FormControl, FormLabel, Textarea, Input, Select, Button } from "@chakra-ui/react";
import "./CommunityStyles.css";

const CreateCommunity = () => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [interests, setInterests] = useState([]);
    const [interestList, setInterestList] = useState([]);
    const [loading, setLoading] = useState(false);
    const [userid, setUserid] = useState(2);

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await fetch('https://commune-dev-csci5308-server.onrender.com/interest');
                if (response.ok) {
                    const responseData = await response.json();
                    setInterestList(responseData);
                    setLoading(false);
                }
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
    }, []);

    const handleNameChange = (event) => {
        setName(event.target.value);
    };

    const handleDescriptionChange = (event) => {
        setDescription(event.target.value);
    };

    const handleInterestChange = (event) => {
        const selectedInterests = Array.from(event.target.options)
            .filter((option) => option.selected)
            .map((option) => option.value);
        setInterests(selectedInterests);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        // Perform the create community logic here
        console.log('Name:', name);
        console.log('Description:', description);
        console.log('Interests:', interests);

        try {

            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ created_by: userid, name: name, description: description, display_image: "link" })
            };

            const response = await fetch('https://commune-dev-csci5308-server.onrender.com/community', requestOptions);

            const postInterestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' }
            }

            // const res = await fetch('https://commune-dev-csci5308-server.onrender.com')

            if (response.ok) {
                //navigate('/users');
            } else {

            }

        } catch (error) {
            console.error(error);
        }

        // Reset the form fields
        setName('');
        setDescription('');
        setInterests([]);
    };

    return (
        <Box className="main-div">
            <Box className="container-div">
                <Heading>Create a community</Heading>
                <form onSubmit={handleSubmit}>
                    <FormControl id="name" marginTop="16px">
                        <FormLabel>Name</FormLabel>
                        <Input
                            type="text"
                            placeholder="Enter community name"
                            value={name}
                            onChange={handleNameChange}
                        />
                    </FormControl>

                    <FormControl id="description" marginTop="16px">
                        <FormLabel>Description</FormLabel>
                        <Textarea
                            rows={3}
                            placeholder="Enter community description"
                            value={description}
                            onChange={handleDescriptionChange}
                        />
                    </FormControl>

                    <FormControl id="interests" marginTop="16px">
                        <FormLabel>Interests</FormLabel>
                        <Select multiple onChange={handleInterestChange} h="30vh">
                            {loading ? null : interestList.map((item, key) => (
                                <option value={item} key={key}>
                                    {item.interestName}
                                </option>
                            ))}
                        </Select>
                    </FormControl>

                    <Button variant="solid" type="submit" marginTop="32px">
                        Create
                    </Button>
                </form>
            </Box>
        </Box>
    );
};

export default CreateCommunity;