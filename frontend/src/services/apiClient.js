import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
});

export function signup(payload) {
  return api.post('/auth/signup', payload).then((res) => res.data);
}

export function updateRoles(userId, roles) {
  return api.put(`/users/${userId}/roles`, { roles });
}

export function saveShopProfile(userId, profileData) {
  return api.post('/shops/profile', profileData, {
    headers: { 'X-User-Id': userId }
  }).then((res) => res.data);
}

export function getShopProfile(userId) {
  return api.get('/shops/profile', {
    headers: { 'X-User-Id': userId }
  }).then((res) => res.data);
}

export function calculateReadinessScore(userId) {
  return api.post('/scoring/calculate', {}, {
    headers: { 'X-User-Id': userId }
  }).then((res) => res.data);
}

export function getReadinessScore(userId) {
  return api.get('/scoring/score', {
    headers: { 'X-User-Id': userId }
  }).then((res) => res.data);
}
