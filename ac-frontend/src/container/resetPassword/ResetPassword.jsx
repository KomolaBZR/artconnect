import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import LinkMa from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {useEffect, useState} from "react";
import { ApiService } from "../../lib/api";
import {Link, useNavigate, useLocation} from "react-router-dom";
import Modul from "../../components/Modul/Modul";
import CircularProgress from '@mui/material/CircularProgress';

const FORGET_URL = "/forgot-password"
function Copyright(props) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <LinkMa
                color="inherit" href="https://mui.com/">
                Your Website
            </LinkMa>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

// TODO remove, this demo shouldn't need to reset the theme.

const defaultTheme = createTheme();

export default function ResetPassword() {
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const token = searchParams.get('token');
    const navigate = useNavigate();
    const [pwd, setPwd] = useState("");
    const [matchPwd, setMatchPwd] = useState("");
    const [validMatch, setValidMatch] = useState(false);
    const [msg, setMsg] = useState("");
    const [resetPassword, setResetPassword] = useState(false);
    const [error, setError] = useState(false);
    const [loading, setLoading] = useState(false);

    //Hook that makes sure both passwords are the same
    useEffect(() => {
        setValidMatch(pwd === matchPwd);
    }, [pwd, matchPwd]);


    const handleSubmit = async (event) => {
        event.preventDefault();
        setLoading(true);
        if(pwd !== matchPwd){
            setMsg("the password don't match. Please choose matching Passwords");
            setError(true);
            setResetPassword(false);
        }else{
        const response = await ApiService.postResetPassword({
            password: pwd,
            token: token
        });
        if(response === "success"){
            setLoading(false);
            setMsg("your password was successfully resetted");
            setResetPassword(true);
            console.log("success");
            navigate("/protected", { state: { message: "login message" } });
        }else{
            setMsg(response);
            setError(true);
            setResetPassword(false);
        }
        console.log("handle submit");}
    };

    return (
        <ThemeProvider theme={defaultTheme}>
            <Grid container component="main" sx={{ height: '100vh' }}>
                {resetPassword &&
                    <Modul data={{
                        message: msg,
                        success:true,
                        error: false,
                        type: "resetPassword"
                    }}/>
                }
                {error &&
                    <Modul data={{
                        message: msg,
                        success:false,
                        error:true,
                        type: "resetPassword"
                    }}/>
                }
                <CssBaseline />
                <Grid
                    item
                    xs={false}
                    sm={4}
                    md={7}
                    sx={{
                        backgroundImage: 'url(https://source.unsplash.com/random?wallpapers)',
                        backgroundRepeat: 'no-repeat',
                        backgroundColor: (t) =>
                            t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
                        backgroundSize: 'cover',
                        backgroundPosition: 'center',
                    }}
                />
                <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
                    <Box
                        sx={{
                            my: 8,
                            mx: 4,
                            display: 'flex',
                            flexDirection: 'column',
                            alignItems: 'center',
                        }}
                    >
                        <Avatar sx={{ m: 1, bgcolor: 'secondary.light' }}>
                            {loading ?<CircularProgress /> :<LockOutlinedIcon />}
                        </Avatar>
                        <Typography component="h1" variant="h5">
                            Forgot Password
                        </Typography>
                        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
                            <TextField
                                margin="normal"
                                type="password"
                                required
                                fullWidth
                                placeholder="Enter your password"
                                id="password"
                                label="Password"
                                name="password"
                                autoComplete="email"
                                autoFocus
                                onChange={(e) => setPwd(e.target.value)}
                                value={pwd}
                            />
                            <TextField
                                type="password"
                                placeholder="Confirm your password"
                                onChange={(e) => setMatchPwd(e.target.value)}
                                value={matchPwd}
                                margin="normal"
                                required
                                fullWidth
                                id="confirmPassword"
                                label="Confirm Password"
                                name="matchPwd"
                                autoComplete="matchPwd"
                                autoFocus
                                onChange={(e) => setMatchPwd(e.target.value)}
                                value={matchPwd}
                            />
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                sx={{ mt: 3, mb: 2 }}
                            >
                                Reset password
                            </Button>
                            <Grid container>
                                <Grid item xs>
                                </Grid>
                                <Grid item>
                                    <Link to="/Register" className="body2">
                                        Don't have an account? Sign Up
                                    </Link>
                                </Grid>
                            </Grid>
                            <Copyright sx={{ mt: 5 }} />
                        </Box>
                    </Box>
                </Grid>
            </Grid>
        </ThemeProvider>
    );
}