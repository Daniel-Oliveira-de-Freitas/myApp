/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import PostComponent from '@/entities/post/post.vue';
import PostClass from '@/entities/post/post.component';
import PostService from '@/entities/post/post.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Post Management Component', () => {
    let wrapper: Wrapper<PostClass>;
    let comp: PostClass;
    let postServiceStub: SinonStubbedInstance<PostService>;

    beforeEach(() => {
      postServiceStub = sinon.createStubInstance<PostService>(PostService);
      postServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<PostClass>(PostComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          postService: () => postServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      postServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllPosts();
      await comp.$nextTick();

      // THEN
      expect(postServiceStub.retrieve.called).toBeTruthy();
      expect(comp.posts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      postServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removePost();
      await comp.$nextTick();

      // THEN
      expect(postServiceStub.delete.called).toBeTruthy();
      expect(postServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
