import Api from '/static/js/common/module/Api.js';
import {input} from '/static/js/common/module/utills/input.js';
import {number} from '/static/js/common/module/utills/number.js';
import {date} from '/static/js/common/module/utills/date.js';
import SessionStorage from '/static/js/common/module/SessionStorage.js';

export const api = Api; // API 모듈 내보내기
export const sessionStorage = SessionStorage;

export const utills = {
    input,
    number,
    date
};
