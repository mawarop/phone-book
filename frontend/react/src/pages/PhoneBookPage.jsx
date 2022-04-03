import {Button, Form, Pagination, Table} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import ContactService from "../services/ContactService";

function PhoneBookPage(props) {

    const [totalPages, setTotalPages] = useState(null);
    const [currentPage, setCurrentPage] = useState(null);
    const [contacts, setContacts] = useState(null);
    // const [isDataFilteredByForm, setIsDataFilteredByForm] = useState(false);
    const [contactTextInput, setContactTextInput] = useState(null);

    function handleSubmit(event) {
        event.preventDefault();
        event.stopPropagation();
        let contactTextInput = event.target.elements.contactTextInput.value;
        setContactTextInput(contactTextInput);
        // let contactsPromise = ContactService.getContacts(0, contactTextInput);
        // contactsPromise.then(res=>{
        //     setContacts(res.data.contacts);
        //
        //     // setIsDataFilteredByForm(true);
        // }).catch(err => {console.log(err)})
    }

    useEffect(() => {
        fetchPageData()
    }, []);

    useEffect(() => {
        fetchPageData()
    }, [contactTextInput]);

    function fetchPageData(page = 0) {
        let contactPromise;

        if (contactTextInput) {
            contactPromise = ContactService.getContacts(page, contactTextInput);
        } else {
            contactPromise = ContactService.getContacts(page);
        }
        contactPromise
            .then((res) => {
                setContacts(res.data.contacts);
                setTotalPages(res.data.totalPages);
                setCurrentPage(res.data.currentPage);
            })
            .catch((error) => console.log(error));

    }

    function updateContactHandler(event) {

    }

    function deleteContactHandler(id) {

    }

    if (contacts)
        return (
            <>
                <Form
                    onSubmit={handleSubmit}
                >
                    <Form.Group className="mb-2">
                        <Form.Label for="contactTextInput">Szukaj kontaktów: </Form.Label>
                        <Form.Control
                            type="text"
                            name="contactTextInput"
                            className="form-control"
                            id="contactTextInput"
                            aria-describedby="contactTextInputHelp"
                            placeholder="Wprowadź imię i nazwisko"
                        />

                        <Button className="mt-2" type="submit" variant="primary">
                            Szukaj
                        </Button>
                    </Form.Group>
                </Form>


                <Table className="my-3" striped bordered hover>
                    <thead>
                    <tr>
                        <th>Imię</th>
                        <th>Nazwisko</th>
                        <th>Numer telefonu domowego</th>
                        <th>Numer telefonu służbowego</th>
                        <th>Email</th>
                        <th colSpan={2}>Akcje</th>
                    </tr>
                    </thead>

                    <tbody>
                    {contacts.map((contact) => {
                        return (
                            <tr>
                                <td>{contact.firstName}</td>
                                <td>{contact.lastName}</td>
                                <td>{contact.homePhoneNumber}</td>
                                <td>{contact.businessPhoneNumber}</td>
                                <td>{contact.email}</td>
                                <td>
                                    <Button
                                        onClick={() => {
                                            updateContactHandler(contact.id, contact);
                                        }}
                                    >
                                        Aktualizuj
                                    </Button>
                                </td>
                                <td>
                                    <Button
                                        variant="secondary"
                                        onClick={() => {
                                            deleteContactHandler(contact.id);
                                        }}
                                    >
                                        Usuń
                                    </Button>
                                </td>
                            </tr>
                        );
                    })}
                    </tbody>
                </Table>

                {totalPages !== null &&
                    currentPage !== null &&
                    (() => {
                        let active = currentPage + 1;
                        let items = [];
                        for (let number = 1; number <= totalPages; number++) {
                            items.push(
                                <Pagination.Item
                                    key={number}
                                    onClick={() => {
                                        fetchPageData(number - 1);
                                        console.log(number);
                                    }}
                                    active={number === active}
                                >
                                    {number}
                                </Pagination.Item>
                            );
                        }
                        return (
                            <div className="text-center">
                                <Pagination>{items}</Pagination>
                            </div>
                        );
                    })()}

            </>
        );
}

export default PhoneBookPage;