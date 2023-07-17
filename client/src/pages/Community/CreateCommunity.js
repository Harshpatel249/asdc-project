import React, { useState, useEffect } from 'react';
import { Form, Button } from 'react-bootstrap';
import "./CommunityStyles.css";

const CreateCommunity = () => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [interests, setInterests] = useState([]);
    const [interestList, setInterestList] = useState([]);
    const [loading, setLoading] = useState(false);

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

    const handleSubmit = (event) => {
        event.preventDefault();
        // Perform the create community logic here
        console.log('Name:', name);
        console.log('Description:', description);
        console.log('Interests:', interests);
        // Reset the form fields
        setName('');
        setDescription('');
        setInterests([]);
    };

    return (
        <div className="main-div">
            <div className="container-div">
                <h1>Create a community</h1>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="name">
                        <Form.Label style={{marginTop: "16px"}}>Name</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Enter community name"
                            value={name}
                            onChange={handleNameChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="description">
                        <Form.Label style={{marginTop: "16px"}}>Description</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows={3}
                            placeholder="Enter community description"
                            value={description}
                            onChange={handleDescriptionChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="interests">
                        <Form.Label style={{marginTop: "16px"}}>Interests</Form.Label>
                        <Form.Control
                            as="select"
                            multiple
                            onChange={handleInterestChange}
                        >
                            {
                                loading? <></>:
                                interestList.map((item, key) => (
                                    <option value={item.interestName} key={key}>{item.interestName}</option>
                                ))
                            }
                            
                        </Form.Control>
                    </Form.Group>

                    <Button variant="primary" type="submit" style={{marginTop: "32px"}}>
                        Create
                    </Button>
                </Form>
            </div>
        </div>
    );
};

export default CreateCommunity;