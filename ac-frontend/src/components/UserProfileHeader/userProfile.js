import React, {Fragment, useEffect, useState} from 'react'
import {useNavigate, Link} from "react-router-dom";
import EmptyGalerie from '../../container/Galerie/Galerie2'
import {EnvelopeIcon, PhoneIcon} from '@heroicons/react/20/solid'
import {Menu, Transition} from '@headlessui/react'
import {CodeBracketIcon, EllipsisVerticalIcon, FlagIcon, StarIcon} from '@heroicons/react/20/solid'
import {PaperClipIcon} from '@heroicons/react/20/solid'
import {storageService} from "../../lib/localStorage"
import {ApiService} from "../../lib/api";


const profile = {
    name: 'Vyacheslav Thomas',
    email: 'Vyacheslav@example.com',
    web: 'Vyacheslav.com',
    avatar: 'https://images.unsplash.com/photo-1463453091185-61582044d556?ixlib=rb-=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=8&w=1024&h=1024&q=80',
    backgroundImage: 'https://images.unsplash.com/photo-1444628838545-ac4016a5418a?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80',
    fields: [['Phone', '(555) 123-4567'], ['Email', 'Vyacheslav@example.com'], ['Title', 'Senior Front-End Developer'], ['Team', 'Product Development'], ['Location', 'San Francisco'], ['Sits', 'Oasis, 4th floor'], ['Salary', '12$'], ['Birthday', 'June 8, 1990'],],
}

function classNames(...classes) {
    return classes.filter(Boolean).join(' ')
}

const Profile = () => {
    const [user, setUser] = useState([])
    const [image, setImage] = useState([]);
    //lad die Userdaten aus dem Backend, wenn es ein userFoto gibt, convertiert er es in eine brauchbare URL
    useEffect(() => {
        async function getUserData() {
            const result = await storageService.getUser();
            const urlGetUser = `http://localhost:8080/users?email=${result}`.replace(/"/g, '');
            console.log("userProfile: " + urlGetUser)
            const userProfile = await ApiService.getDataSecuredWithParameter(urlGetUser);
            console.log("userProfile- userProfile: " + JSON.stringify(userProfile));
            setUser(userProfile.data);

            if (userProfile.data.profilePhoto.image.data == undefined) {
                const updatedUser = {...user, newAttribute: 'no immage'};
                setUser(updatedUser);
                //Blank Picture
                console.log("user Data undefined.")
            } else {
                const byteCharacters = atob(userProfile.data.profilePhoto.image.data);
                const byteNumbers = new Array(byteCharacters.length);
                for (let i = 0; i < byteCharacters.length; i++) {
                    byteNumbers[i] = byteCharacters.charCodeAt(i);
                }
                const byteArray = new Uint8Array(byteNumbers);
                // Create URL for the binary image data
                const blob = new Blob([byteArray], {type: 'image/png'}); // Adjust the 'type' according to the actual image format
                const url = URL.createObjectURL(blob);
                setImage(url);
                console.log("url: " + url);
            }

        }

        getUserData();

    }, [])

    const navigate = useNavigate();

    return (<>
            <div>
                <div>
                    <div>
                        <img className="h-32 w-full object-cover lg:h-48" src={profile.backgroundImage} alt=""/>
                    </div>
                    <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
                        <div className="-mt-12 sm:-mt-16 sm:flex sm:items-end sm:space-x-5">
                            <div className="flex">
                                <img className="h-24 w-24 rounded-full ring-4 ring-white sm:h-32 sm:w-32"
                                     src={image} alt=""/>
                            </div>
                            <div
                                className="mt-6 sm:flex sm:min-w-0 sm:flex-1 sm:items-center sm:justify-end sm:space-x-6 sm:pb-1">
                                <div className="mt-6 min-w-0 flex-1 sm:hidden md:block">
                                    <h1 className="truncate text-2xl font-bold text-gray-900">{(user.firstname ? user.firstname + " " : " ") + (user.lastname ? user.lastname : " ")}</h1>
                                </div>
                                <div
                                    className="mt-6 flex flex-col justify-stretch space-y-3 sm:flex-row sm:space-x-4 sm:space-y-0">
                                    <button
                                        onClick={() => {
                                            navigate("/bearbeiten", { state: { user: user, imageProps: image } });
                                        }}
                                        type="button"
                                        className="inline-flex justify-center rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                                    >
                                        <span>Edit</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div className="mt-6 hidden min-w-0 flex-1 sm:block md:hidden">
                            <h1 className="truncate text-2xl font-bold text-gray-900">{(user.firstname ? user.firstname + " " : " ") + (user.lastname ? user.lastname : " ")}</h1>
                        </div>
                    </div>
                    <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
                                                                        <span className="flex-grow">
{(user.biography ? user.biography + " " : " ")}
              </span>
                    </div>
                </div>
                <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
                    <div className="mx-auto max-w-3xl">
                    </div>
                    <div className="mt-6 border-t border-gray-100">

                    </div>
                </div>
            </div>
        </>

    )
}

export default Profile